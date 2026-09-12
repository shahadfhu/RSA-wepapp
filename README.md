# RSA Web App

A simple Spring Boot web application that demonstrates RSA public-key encryption and decryption through a browser interface. Enter a message, encrypt it with a generated RSA key pair, and decrypt it back — all in real time.

> * **Educational project.** This app is meant to demonstrate how RSA encryption works, not to be used as a production-grade secure messaging system. See [Known Limitations](#known-limitations) below.

---

## Features

- Generates a 2048-bit RSA key pair on startup
- Encrypts plaintext messages using the public key
- Decrypts ciphertext back to plaintext using the private key
- Simple, single-page HTML interface (Thymeleaf)
- Dockerized and ready for deployment (Heroku-style `Procfile` included)

---

## Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java, Spring Boot |
| Frontend | HTML, Thymeleaf |
| Build tool | Maven |
| Encryption | Java Cryptography Architecture (`java.security`, `javax.crypto`) |
| Deployment | Docker / Procfile (Heroku-compatible) |

---

## How It Works

1. On application startup, a 2048-bit RSA key pair (public + private) is generated and held in memory.
2. **Encrypt:** the user submits a plaintext message → it's encrypted with the public key → the Base64-encoded ciphertext is displayed.
3. **Decrypt:** the user submits a Base64-encoded ciphertext → it's decrypted with the private key → the original plaintext is displayed.

---

## Getting Started

### Prerequisites

- Java 17+
- Maven (or use the included `mvnw` wrapper)

### Run locally

```bash
git clone https://github.com/shahadfhu/RSA-wepapp.git
cd RSA-wepapp
./mvnw spring-boot:run
```

Then open your browser at:

```
http://localhost:8080
```

### Run with Docker

```bash
docker build -t rsa-webapp .
docker run -p 8080:8080 rsa-webapp
```

---

## Project Structure

```
RSA-wepapp/
├── src/                 # Application source code (controllers, templates)
├── out/artifacts/        # Build output (not tracked — see note below)
├── pom.xml               # Maven project configuration
├── Dockerfile            # Container build definition
├── Procfile              # Heroku deployment entry point
└── mvnw / mvnw.cmd       # Maven wrapper scripts
```

---

## Known Limitations

- **Shared key pair:** the RSA key pair is generated once per application instance, so all visitors share the same public/private key — this is fine for a demo, but not suitable for real multi-user encryption.
- **Padding scheme:** uses default PKCS#1 padding rather than OAEP; a production system should use `RSA/ECB/OAEPWithSHA-256AndMGF1Padding` for stronger security guarantees.
- **Message size limit:** RSA can only encrypt messages smaller than the key size allows (~245 bytes for a 2048-bit key with PKCS#1 padding). Longer messages will fail.

---

## License

This project is licensed under the MIT License.
