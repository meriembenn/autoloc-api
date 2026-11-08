package tn.esprit.autoloc.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Erreurs de validation (@Valid) → 400 avec le détail de chaque champ
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex,
                                                          HttpServletRequest request) {
        Map<String, String> erreurs = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> erreurs.put(e.getField(), e.getDefaultMessage()));
        return build(HttpStatus.BAD_REQUEST, "Erreur de validation", request, erreurs);
    }

    // 2. Ressource introuvable → 404
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex,
                                                        HttpServletRequest request) {
        return build(HttpStatus.NOT_FOUND, ex.getMessage(), request, null);
    }

    // 3. JSON illisible (virgule manquante, valeur d'enum inconnue...) → 400
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleJsonInvalide(HttpMessageNotReadableException ex,
                                                            HttpServletRequest request) {
        return build(HttpStatus.BAD_REQUEST,
                "JSON invalide ou mal formé (vérifiez la syntaxe et les valeurs des enums)",
                request, null);
    }

    // 4. Contrainte de la base violée (doublon, clé étrangère...) → 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleConflit(DataIntegrityViolationException ex,
                                                       HttpServletRequest request) {
        return build(HttpStatus.CONFLICT,
                "Opération impossible : donnée en double ou encore liée à d'autres données",
                request, null);
    }

    // 5. Toute autre erreur imprévue → 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAutre(Exception ex, HttpServletRequest request) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR,
                "Erreur interne du serveur", request, null);
    }

    // Méthode utilitaire : construit la réponse d'erreur
    private ResponseEntity<ErrorResponse> build(HttpStatus status, String message,
                                                HttpServletRequest request,
                                                Map<String, String> erreurs) {
        ErrorResponse body = new ErrorResponse(
                LocalDateTime.now(ZoneId.of("Africa/Tunis")),
                status.value(),
                message,
                request.getRequestURI(),
                erreurs
        );
        return ResponseEntity.status(status).body(body);
    }
}