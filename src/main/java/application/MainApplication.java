package application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Block;
import domain.BlockChain;
import domain.Transaction;
import lombok.extern.java.Log;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

@Log
public class MainApplication {

    public static void main(String[] args) throws JsonProcessingException {

        final List<Transaction> transactions1 = Arrays.asList(
                Transaction.builder()
                        .transactionId(UUID.randomUUID().toString())
                        .timeStamp(LocalDateTime.now().toString())
                        .amount(10)
                        .transmitter("Carlos")
                        .receiver("Mario")
                        .build(),
                Transaction.builder()
                        .transactionId(UUID.randomUUID().toString())
                        .timeStamp(LocalDateTime.now().toString())
                        .amount(2)
                        .transmitter("Carlos")
                        .receiver("Juan")
                        .build(),
                Transaction.builder()
                        .transactionId(UUID.randomUUID().toString())
                        .timeStamp(LocalDateTime.now().toString())
                        .amount(5)
                        .transmitter("Mario")
                        .receiver("Sara")
                        .build()
        );

        final List<Transaction> transactions2 = Arrays.asList(
                Transaction.builder()
                        .transactionId(UUID.randomUUID().toString())
                        .timeStamp(LocalDateTime.now().toString())
                        .amount(20)
                        .transmitter("Sara")
                        .receiver("Susana")
                        .build(),
                Transaction.builder()
                        .transactionId(UUID.randomUUID().toString())
                        .timeStamp(LocalDateTime.now().toString())
                        .amount(15)
                        .transmitter("Sara")
                        .receiver("Carolina")
                        .build()
        );

        //Cración del BlockChain
        final BlockChain blockchain = new BlockChain();

        log.log(Level.INFO, "Minando bloque 1...");
        blockchain.addNewBlock(new Block(transactions1));

        log.log(Level.INFO, "Minando bloque 2...");
        blockchain.addNewBlock(new Block(transactions2));

        //Mostrar en pantalla el BlockChain
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter()
                .writeValueAsString(blockchain.getChain()));

        //El BlockChain es válido?
        log.log(Level.INFO, "Is Blockchain valid: " + blockchain.isChainValid());

        //Se altera la transacción del primer bloque
        blockchain.getChain().get(1).setTransactions(
                Collections.singletonList(
                        Transaction.builder()
                                .transactionId(UUID.randomUUID().toString())
                                .timeStamp(LocalDateTime.now().toString())
                                .amount(2000)
                                .transmitter("Carlos")
                                .receiver("Juan")
                                .build()));

        //El BlockChain sigue siendo válido?
        log.log(Level.INFO, "Is Blockchain valid: " + blockchain.isChainValid());

    }
}
