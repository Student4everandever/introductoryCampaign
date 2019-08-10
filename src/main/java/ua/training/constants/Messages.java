package ua.training.constants;

public interface Messages {

    String VALIDATION_FAIL = "Wrong symbols enter attempt.";

    String LOGIN_LOGGED_ALREADY = "Logged user %s %s tried to sign up.";
    String LOGIN_SUCCESSFUL_LOGIN = "%s %s logged successfully.";
    String LOGIN_FAIL_LOGIN = "Sign in fail with login: %s.";

    String REGISTRATION_ALREADY_EXIST = "Attempt to register with existing login - %s or eMail - %s.";
    String REGISTRATION_SUCCESSFUL_REGISTRATION = "%s %s registered successfully.";
    String REGISTRATION_FAIL_REGISTRATION = "Registration fail. Login %s already exists or data invalid.";

    String LOG_LOGOUT = "%s logged out.";

    String ADMIN_ADD_SPECIALTY_ALREADY_EXIST = "Attempt to create existing specialty %s, %s.";
    String ADMIN_ADD_SPECIALTY_SUCCESS = "Specialty %s, %s was successfully created.";
    String ADMIN_ADD_SUBJECT_ALREADY_EXIST = "Attempt to create existing subject %s, %s.";
    String ADMIN_ADD_SUBJECT_SUCCESS = "Subject %s, %s was successfully created.";
    String ADMIN_ADD_UNIVERSITY_ALREADY_EXIST = "Attempt to create existing university %s, %s.";
    String ADMIN_ADD_UNIVERSITY_SUCCESS = "University %s, %s was successfully created.";
    String ADMIN_DELETE_SPECIALTY_FAIL = "Specialty deleting failed.";
    String ADMIN_DELETE_SPECIALTY_SUCCESS = "Specialty with id %s was successfully deleted.";
    String ADMIN_DELETE_UNIVERSITY_FAIL = "University deleting failed.";
    String ADMIN_DELETE_UNIVERSITY_SUCCESS = "University with id %s was successfully deleted.";
    String ADMIN_EDIT_SPECIALTY_SUCCESS = "Specialty %s, %s was successfully updated.";
    String ADMIN_EDIT_SPECIALTY_ALREADY_EXIST = "Attempt to create existing specialty %s, %s.";
    String ADMIN_EDIT_UNIVERSITY_NAME = "University name was updated successfully for university %s.";
    String ADMIN_EDIT_UNIVERSITY_SPECIALTIES = "Specialties were updated successfully fo university %s.";
    String ADMIN_MAIL_COMMAND_SENT_SUCCESS = "Emails were sent successfully to applicants %s.";
    String ADMIN_PUT_MARKS_SUCCESS = "Marks were entered successfully for applicant %s.";

    String APPLICANT_CHOOSE_SUBJECTS_REGISTERED = "Applicant %s registered to pass the exams.";
    String APPLICANT_CHOOSE_SUBJECTS_FAIL = "Attempt to registered to pass the exams failed.";
    String APPLICANT_CHOOSE_SPECIALTY_SECOND_REGISTRATION_ATTEMPT = "User %s tried to register second time.";

    String SEND_MAIL_PROPERTIES_LOADING_FAIL = "Failed to load properties to send emails.";
    String SEND_MAIL_FAIL = "Failed to send emails.";

    String CONNECTION_POOL_DATA_FAIL = "Failed to get DataSource for DB connection.";

    String JDBC_SPECIALTY_FIND_BY_ID_FAIL = "Failed to find specialty with id %s.";
    String JDBC_SPECIALTY_FIND_ALL_FAIL = "Failed to find all specialties.";
    String JDBC_SPECIALTY_DELETE_FAIL = "Failed to delete specialty with id %s.";
    String JDBC_SPECIALTY_FIND_BY_NAME_FAIL = "Failed to find specialty with titles: %s and %s.";
    String JDBC_SPECIALTY_FIND_FOR_UNIVERSITY_FAIL = "Failed to find specialties for university %s.";
    String JDBC_SPECIALTY_FIND_NON_UNIVERSITY_FAIL = "Failed to find specialties not in university %s.";
    String JDBC_SPECIALTY_UPDATE_WITH_SUBJECTS_FAIL = "Failed to update specialty %s to change subjects.";
    String JDBC_SPECIALTY_CREATE_WITH_UNIVERSITY_AND_SUBJECT_FAIL = "Failed to create specialty with titles: %s and %s with university and subjects.";
    String JDBC_SPECIALTY_FIND_BY_STRING_NAME_FAIL = "Failed to find specialty by string %s.";
    String JDBC_SPECIALTY_FIND_PER_PAGE = "Failed to find specialties per page for page %s with %s rows.";

    String JDBC_SUBJECT_CREATION_FAIL = "Failed to create new subject with names: %s and %s.";
    String JDBC_SUBJECT_FIND_ALL_FAIL = "Failed to find all subjects.";
    String JDBC_SUBJECT_FIND_BY_NAME_FAIL = "Failed to find subject with names: %s and %s.";
    String JDBC_SUBJECT_FIND_BY_STRING_NAME_FAIL = "Failed to find subject by string name %s.";
    String JDBC_SUBJECT_FIND_BY_SPECIALTY_AND_NUMBER_FAIL = "Failed to find subject number %s for specialty %s.";
    String JDBC_SUBJECT_FIND_ALL_BUT_FIRST_FAIL = "Failed to find all but first subjects.";
    String JDBC_SUBJECT_ADD_SUBJECTS_TO_USER_FAIL = "Failed to add subjects to user %s.";
    String JDBC_SUBJECT_FIND_SUBJECTS_OF_USER_FAIL = "Failed to find subjects of user %s.";

    String JDBC_UNIVERSITY_CREATION_FAIL = "Failed to create new university with names: %s and %s.";
    String JDBC_UNIVERSITY_FIND_BY_ID_FAIL = "Failed to find university with id: %s.";
    String JDBC_UNIVERSITY_FIND_ALL_FAIL = "Failed to find all universities.";
    String JDBC_UNIVERSITY_UPDATE_FAIL = "Failed to update university %s.";
    String JDBC_UNIVERSITY_UPDATE_SPECIALTIES_FAIL = "Failed to update specialties for university %s.";
    String JDBC_UNIVERSITY_FIND_BY_STRING_NAME_FAIL = "Failed to find university by string name %s.";
    String JDBC_UNIVERSITY_FIND_PER_PAGE_FAIL = "Failed to find universities per page for page %s with %s rows.";
    String JDBC_UNIVERSITY_DELETE_FAIL = "Failed to delete university with id %s.";
    String JDBC_UNIVERSITY_FIND_BY_NAME_FAIL = "Failed to find university with names: %s and %s.";

    String JDBC_USER_CREATION_FAIL = "Failed to create new user with email: %s.";
    String JDBC_USER_FIND_ALL_APPLICANTS_FAIL = "Failed to find all users applied for exams.";
    String JDBC_USER_FINDING_FAIL = "Failed to find user with login: %s.";
    String JDBC_USER_FINDING_WITH_EXAMS_FAIL = "Failed to find users with exams.";
    String JDBC_USER_PUTTING_MARKS_FAIL = "Failed to put marks for user %s.";
    String JDBC_USER_FINDING_USER_MARKS_FAIL = "Failed to find marks for user %s.";
    String JDBC_USER_FINDING_WITH_RATING_FAIL = "Failed to find user with rating.";
    String JDBC_USER_GET_PER_PAGE = "Failed to find users per page for page %s with %s rows.";
    String JDBC_USER_FINDING_BY_LOGIN_OR_EMAIL_FAIL = "Failed to find users by login %s or email %s.";
    String JDBC_USER_FIND_WITH_REQUIRED_RATING_FAIL = "Failed to find users with required rating.";
}
