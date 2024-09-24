package fr.app.miss_meteo_challenge.services.impl.meteo_concept;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum to map the weather code from MeteoConcept to a description and using to get the weather from the code
 */
public enum MeteoConceptWeatherCode {
    SOLEIL(0, "Soleil"),
    PEU_NUAGEUX(1, "Peu nuageux"),
    CIEL_VOILE(2, "Ciel voilé"),
    NUAGEUX(3, "Nuageux"),
    TRES_NUAGEUX(4, "Très nuageux"),
    COUVERT(5, "Couvert"),
    BROUILLARD(6, "Brouillard"),
    BROUILLARD_GIVRANT(7, "Brouillard givrant"),
    PLUIE_FAIBLE(10, "Pluie faible"),
    PLUIE_MODEREE(11, "Pluie modérée"),
    PLUIE_FORTE(12, "Pluie forte"),
    PLUIE_FAIBLE_VERGLACANTE(13, "Pluie faible verglaçante"),
    PLUIE_MODEREE_VERGLACANTE(14, "Pluie modérée verglaçante"),
    PLUIE_FORTE_VERGLACANTE(15, "Pluie forte verglaçante"),
    BRUINE(16, "Bruine"),
    NEIGE_FAIBLE(20, "Neige faible"),
    NEIGE_MODEREE(21, "Neige modérée"),
    NEIGE_FORTE(22, "Neige forte"),
    PLUIE_NEIGE_MELEES_FAIBLES(30, "Pluie et neige mêlées faibles"),
    PLUIE_NEIGE_MELEES_MODEREES(31, "Pluie et neige mêlées modérées"),
    PLUIE_NEIGE_MELEES_FORTES(32, "Pluie et neige mêlées fortes"),
    AVERSES_PLUIE_LOCALES_FAIBLES(40, "Averses de pluie locales et faibles"),
    AVERSES_PLUIE_LOCALES(41, "Averses de pluie locales"),
    AVERSES_LOCALES_FORTES(42, "Averses locales et fortes"),
    AVERSES_PLUIE_FAIBLES(43, "Averses de pluie faibles"),
    AVERSES_PLUIE(44, "Averses de pluie"),
    AVERSES_PLUIE_FORTES(45, "Averses de pluie fortes"),
    AVERSES_PLUIE_FAIBLES_FREQUENTES(46, "Averses de pluie faibles et fréquentes"),
    AVERSES_PLUIE_FREQUENTES(47, "Averses de pluie fréquentes"),
    AVERSES_PLUIE_FORTES_FREQUENTES(48, "Averses de pluie fortes et fréquentes"),
    AVERSES_NEIGE_LOCALISEES_FAIBLES(60, "Averses de neige localisées et faibles"),
    AVERSES_NEIGE_LOCALISEES(61, "Averses de neige localisées"),
    AVERSES_NEIGE_LOCALISEES_FORTES(62, "Averses de neige localisées et fortes"),
    AVERSES_NEIGE_FAIBLES(63, "Averses de neige faibles"),
    AVERSES_NEIGE(64, "Averses de neige"),
    AVERSES_NEIGE_FORTES(65, "Averses de neige fortes"),
    AVERSES_NEIGE_FAIBLES_FREQUENTES(66, "Averses de neige faibles et fréquentes"),
    AVERSES_NEIGE_FREQUENTES(67, "Averses de neige fréquentes"),
    AVERSES_NEIGE_FORTES_FREQUENTES(68, "Averses de neige fortes et fréquentes"),
    AVERSES_PLUIE_NEIGE_MELEES_LOCALISEES_FAIBLES(70, "Averses de pluie et neige mêlées localisées et faibles"),
    AVERSES_PLUIE_NEIGE_MELEES_LOCALISEES(71, "Averses de pluie et neige mêlées localisées"),
    AVERSES_PLUIE_NEIGE_MELEES_LOCALISEES_FORTES(72, "Averses de pluie et neige mêlées localisées et fortes"),
    AVERSES_PLUIE_NEIGE_MELEES_FAIBLES(73, "Averses de pluie et neige mêlées faibles"),
    AVERSES_PLUIE_NEIGE_MELEES(74, "Averses de pluie et neige mêlées"),
    AVERSES_PLUIE_NEIGE_MELEES_FORTES(75, "Averses de pluie et neige mêlées fortes"),
    AVERSES_PLUIE_NEIGE_MELEES_FAIBLES_NOMBREUSES(76, "Averses de pluie et neige mêlées faibles et nombreuses"),
    AVERSES_PLUIE_NEIGE_MELEES_FREQUENTES(77, "Averses de pluie et neige mêlées fréquentes"),
    AVERSES_PLUIE_NEIGE_MELEES_FORTES_FREQUENTES(78, "Averses de pluie et neige mêlées fortes et fréquentes"),
    ORAGES_FAIBLES_LOCAUX(100, "Orages faibles et locaux"),
    ORAGES_LOCAUX(101, "Orages locaux"),
    ORAGES_FORT_LOCAUX(102, "Orages fort et locaux"),
    ORAGES_FAIBLES(103, "Orages faibles"),
    ORAGES(104, "Orages"),
    ORAGES_FORTS(105, "Orages forts"),
    ORAGES_FAIBLES_FREQUENTS(106, "Orages faibles et fréquents"),
    ORAGES_FREQUENTS(107, "Orages fréquents"),
    ORAGES_FORTS_FREQUENTS(108, "Orages forts et fréquents"),
    ORAGES_FAIBLES_LOCAUX_NEIGE_GRESEL(120, "Orages faibles et locaux de neige ou grésil"),
    ORAGES_LOCAUX_NEIGE_GRESEL(121, "Orages locaux de neige ou grésil"),
    ORAGES_LOCAUX_NEIGE_GRESEL_2(122, "Orages locaux de neige ou grésil"),
    ORAGES_FAIBLES_NEIGE_GRESEL(123, "Orages faibles de neige ou grésil"),
    ORAGES_NEIGE_GRESEL(124, "Orages de neige ou grésil"),
    ORAGES_NEIGE_GRESEL_2(125, "Orages de neige ou grésil"),
    ORAGES_FAIBLES_FREQUENTS_NEIGE_GRESEL(126, "Orages faibles et fréquents de neige ou grésil"),
    ORAGES_FREQUENTS_NEIGE_GRESEL(127, "Orages fréquents de neige ou grésil"),
    ORAGES_FREQUENTS_NEIGE_GRESEL_2(128, "Orages fréquents de neige ou grésil"),
    ORAGES_FAIBLES_LOCAUX_PLUIE_NEIGE_GRESEL(130, "Orages faibles et locaux de pluie et neige mêlées ou grésil"),
    ORAGES_LOCAUX_PLUIE_NEIGE_GRESEL(131, "Orages locaux de pluie et neige mêlées ou grésil"),
    ORAGES_FORT_LOCAUX_PLUIE_NEIGE_GRESEL(132, "Orages fort et locaux de pluie et neige mêlées ou grésil"),
    ORAGES_FAIBLES_PLUIE_NEIGE_GRESEL(133, "Orages faibles de pluie et neige mêlées ou grésil"),
    ORAGES_PLUIE_NEIGE_GRESEL(134, "Orages de pluie et neige mêlées ou grésil"),
    ORAGES_FORTS_PLUIE_NEIGE_GRESEL(135, "Orages forts de pluie et neige mêlées ou grésil"),
    ORAGES_FAIBLES_FREQUENTS_PLUIE_NEIGE_GRESEL(136, "Orages faibles et fréquents de pluie et neige mêlées ou grésil"),
    ORAGES_FREQUENTS_PLUIE_NEIGE_GRESEL(137, "Orages fréquents de pluie et neige mêlées ou grésil"),
    ORAGES_FORTS_FREQUENTS_PLUIE_NEIGE_GRESEL(138, "Orages forts et fréquents de pluie et neige mêlées ou grésil"),
    PLUIES_ORAGEUSES(140, "Pluies orageuses"),
    PLUIE_NEIGE_MELEES_ORAGEUX(141, "Pluie et neige mêlées à caractère orageux"),
    NEIGE_ORAGEUX(142, "Neige à caractère orageux"),
    PLUIE_FAIBLE_INTERMITTENTE(210, "Pluie faible intermittente"),
    PLUIE_MODEREE_INTERMITTENTE(211, "Pluie modérée intermittente"),
    PLUIE_FORTE_INTERMITTENTE(212, "Pluie forte intermittente"),
    NEIGE_FAIBLE_INTERMITTENTE(220, "Neige faible intermittente"),
    NEIGE_MODEREE_INTERMITTENTE(221, "Neige modérée intermittente"),
    NEIGE_FORTE_INTERMITTENTE(222, "Neige forte intermittente"),
    PLUIE_NEIGE_MELEES(230, "Pluie et neige mêlées"),
    PLUIE_NEIGE_MELEES_2(231, "Pluie et neige mêlées"),
    PLUIE_NEIGE_MELEES_3(232, "Pluie et neige mêlées"),
    AVERSES_GRELE(235, "Averses de grêle");

    private final int code;
    private final String description;

    private static final Map<Integer, MeteoConceptWeatherCode> CODE_MAP = new HashMap<>();

    static {
        for (MeteoConceptWeatherCode weatherCode : values()) {
            CODE_MAP.put(weatherCode.code, weatherCode);
        }
    }

    /**
     * Constructor
     *
     * @param code        the code of the weather
     * @param description the description of the weather
     */
    MeteoConceptWeatherCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     *
     * @return the description of the weather
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param code the code of the weather
     * @return the weather from the code
     */
    public static MeteoConceptWeatherCode getWeatherByCode(int code) {
        return CODE_MAP.get(code);
    }
}