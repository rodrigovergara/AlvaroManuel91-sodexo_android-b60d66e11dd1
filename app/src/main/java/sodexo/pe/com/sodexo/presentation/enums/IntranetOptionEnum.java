package sodexo.pe.com.sodexo.presentation.enums;

public enum IntranetOptionEnum {
    VIEW_CREDIT("CONSULTA_SALDO"),
    LAST_MOVEMENTS("ULTIMOS_MOVIMIENTOS"),
    PROFILE("DATOS_PERSONALES"),
    CHANGE_CARD_PASSWORD("CAMBIAR_CLAVE_TARJETA"),
    CHANGE_WEB_PASSWORD("CAMBIAR_CLAVE_WEB"),
    SMS_ALERT("ALERTA_SMS"),
    WISHLIST("WISHLIST"),
    BLOG("BLOG"),
    QUIZ("ENCUESTAS"),
    BLOCK_CARD("BLOQUEO"),
    REPLACE_CARD("REPOSICION");
    private String value;

    IntranetOptionEnum(String value) {
        this.value = value;
    }

    public static IntranetOptionEnum getEnumValue(String codigo) {
        for (IntranetOptionEnum optionEnum : IntranetOptionEnum.values()) {
            if (optionEnum.value.equals(codigo)) {
                return optionEnum;
            }
        }
        return VIEW_CREDIT;
    }

}
