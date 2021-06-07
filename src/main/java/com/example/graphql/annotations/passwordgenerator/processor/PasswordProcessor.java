package com.example.graphql.annotations.passwordgenerator.processor;


import com.example.graphql.utils.AESUtils;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

/**
 * Generates password while creating object
 */
public class PasswordProcessor {

    private static String SECRET_KEY = "YuTr45QEsUiOppTy";

    public final String password;

    public PasswordProcessor() {
        CharacterRule digits = new CharacterRule(EnglishCharacterData.Alphabetical);
        PasswordGenerator passwordGenerator = new org.passay.PasswordGenerator();
        String rawPassword = passwordGenerator.generatePassword(10, digits);
        password = AESUtils.encrypt(rawPassword, SECRET_KEY);
    }
}
