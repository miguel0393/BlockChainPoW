package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class BlockChain {

    private final List<Block> chain;
    private final int difficulty;

    public BlockChain() {
        this.difficulty = 4;
        this.chain = new ArrayList<>();
        this.chain.add(createGenesisBlock());
    }

    public Block createGenesisBlock() {
        return Block.builder()
                .transactions(Collections.emptyList())
                .previousHash("0")
                .hash("0")
                .build();
    }

    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    public void addNewBlock(Block newBlock) {
        newBlock.setPreviousHash(getLatestBlock().getHash());
        newBlock.mineBlock(this.difficulty);
        chain.add(newBlock);
    }

    public boolean isChainValid() {
        for (int i = 1; i < this.chain.size(); i++) {
            final Block currentBlock = chain.get(i);
            final Block previousBlock = chain.get(i - 1);

            return ((currentBlock.getHash().equals(currentBlock.calculateHash())) &&
                    (currentBlock.getPreviousHash().equals(previousBlock.getHash())));
        }

        return true;
    }
}
