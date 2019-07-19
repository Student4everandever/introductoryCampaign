package ua.training.constants;

public interface Regex {
    // Cyrillic name
    String REGEX_NAME_UKR = "^[А-ЯІЇЄ][а-яіїє']{1,20}$";
    // Latin name
    String REGEX_NAME_LAT = "^[A-Z][a-z]{1,20}$";
    // Login
    String REGEX_LOGIN = "^[A-Za-z0-9_-]{3,20}$";
    //Password
    String REGEX_PASSWORD = "^[A-Za-z0-9_-]{3,20}$";
    //Email
    String REGEX_E_MAIL = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    //University cyrillic name
    String REGEX_UNIVERSITY_UKR = "^[\"”«».,()А-ЯІЇЄа-яіїє'0-9- ]+$";
    //University latin name
    String REGEX_UNIVERSITY_LAT = "^[\"-”«».,()\\w ]+$";
    //Specialty cyrillic title
    String REGEX_SPECIALTY_UKR = "^[-.,()А-ЯІЇЄа-яіїє'0-9 ]+$";
    //Specialty cyrillic title
    String REGEX_SPECIALTY_LAT = "^[-.,()\\w ]+$";
    //Subject cyrillic title
    String REGEX_SUBJECT_UKR = "^[-.,А-ЯІЇЄа-яіїє'0-9 ]+$";
    //Subject cyrillic title
    String REGEX_SUBJECT_LAT = "^[-.,\\w ]+$";


}