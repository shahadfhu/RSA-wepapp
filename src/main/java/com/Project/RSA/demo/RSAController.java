package com.Project.RSA.demo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.PostConstruct;
import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;
    @Controller
    public class RSAController {

        private KeyPair keyPair;

        public void init() throws NoSuchAlgorithmException {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            keyPair = keyGen.generateKeyPair(); }

        @GetMapping("/")
        public String index() {
            return "index"; }

        @PostMapping("/encrypt")
        public String encrypt(@RequestParam("message") String message, Model model) throws Exception {
            PublicKey publicKey = keyPair.getPublic();

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedBytes = cipher.doFinal(message.getBytes());

            String encrypted = Base64.getEncoder().encodeToString(encryptedBytes);
            model.addAttribute("encrypted", encrypted);
            return "index";}

        @PostMapping("/decrypt")
        public String decrypt(@RequestParam("encryptedMessage") String encryptedMessage, Model model) throws Exception {
            PrivateKey privateKey = keyPair.getPrivate();

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));

            String decrypted = new String(decryptedBytes);
            model.addAttribute("encrypted", encryptedMessage);
            model.addAttribute("decrypted", decrypted);

            return "index";
        }
    }


