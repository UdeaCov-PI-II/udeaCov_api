package co.edu.udea.covapi.messaging;

public class MessagingConstants {

    public static final String NEEDS_APPROVE_TITLE = "Se le ha asignado un permiso";
    public static final String NEEDS_APPROVE_MESSAGE = "El permiso %s necesita su aprobación";
    public static final String APPROVED_TITLE = "Su permiso ha sido aprobado";
    public static final String APPROVED_MESSAGE = "El permiso %s ha sido autorizado";
    public static final String NO_APPROVED_TITLE = "Su permiso no ha sido aprobado";
    public static final String NO_APPROVED_MESSAGE = "El permiso %s no ha sido autorizado. Revise la app para ver los detalles";
    public static final String DEFAULT_TOPIC = "Gestión permisos";

    private MessagingConstants(){
        throw new IllegalStateException("Utility class");
    }
}
