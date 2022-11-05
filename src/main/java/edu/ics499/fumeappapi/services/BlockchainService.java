package edu.ics499.fumeappapi.services;

import edu.ics499.fumeappapi.domain.Block;
import edu.ics499.fumeappapi.domain.Blockchain;
import edu.ics499.fumeappapi.domain.Hash;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BlockchainService {

    public static Block treeGenerator(ArrayList<Block> blocks) throws IOException {
        ArrayList<Block> leafs = new ArrayList<>();

        for(int i = 0; i < blocks.size(); i++) leafs.add(new Block());
        return treeBuilder(leafs);
    }

    private static Block treeBuilder(ArrayList<Block> chain) {
        ArrayList<Block> vertexs = new ArrayList<>();

        while(chain.size() != 1) {
            int index = 0, height = chain.size();
            while(index < height) {
                Block left = chain.get(index);
                Block right = null;

                if((index + 1) < height) {
                    right = chain.get(index + 1);
                } else {
                    right = new Block(null, null, left.getHash());
                }

                String vertexHash = Hash.hashCreation(left.getHash() + right.getHash());
                vertexs.add(new Block(left, right, vertexHash));
                index +=2;

            }
            chain = vertexs;
            vertexs = new ArrayList<>();
        }
        return chain.get(0);
    }

    public static void showBlockchain(Block genesis) {
        if(genesis == null)return;
        if((Block.getLeft() == null && Block.getRight() == null)) System.out.println(genesis.getHash());

        Queue<Block> queue = new LinkedList<>();
        queue.add(genesis);
        queue.add(null);

        while(!queue.isEmpty()) {
            Block cursor = queue.poll();
            if(cursor != null) System.out.println(cursor.getHash());
            else {
                if(!queue.isEmpty()) queue.add(null);
            }
            if(cursor != null && Block.getLeft() != null) queue.add(Block.getLeft());
            if(cursor != null && Block.getRight() != null) queue.add(Block.getRight());
        }

    }

}
