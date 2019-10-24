package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.java.Log;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;
import java.util.logging.Level;

@Log
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class Block {

    private List<Transaction> transactions;
    private String previousHash;
    private String hash;
    private int nonce;

    public Block(List<Transaction> transactions) {
        this.nonce = 0;
        this.transactions = transactions;
    }

    public String calculateHash() {
        return DigestUtils.sha256Hex(this.transactions + this.previousHash + this.nonce);
    }

    public void mineBlock(int difficulty) {
        // Se calcula el Hash hasta que cumpla con la condición de dificultad
        do {
            this.nonce++;
            this.hash = calculateHash();
        } while (!this.hash.substring(0, difficulty)
                .equals(repeatString("0", difficulty)));

        log.log(Level.INFO, "Bloque minado...! " + this.hash);
    }

    public String repeatString(String string, int count) {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            stringBuilder.append(string);
        }

        return stringBuilder.toString();
    }
}
