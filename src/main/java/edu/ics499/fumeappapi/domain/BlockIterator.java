package edu.ics499.fume.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import edu.ics499.fume.entities.Block;

public class BlockIterator implements Iterator<Block> {
	private Block block;
	private Iterator<Block> iterator;
	private Predicate<Block> predicate;
	
	public BlockIterator(Iterator<Block> iterator, Predicate<Block> predicate) {
		this.iterator = iterator;
		this.predicate = predicate;
	}

	@Override
	public boolean hasNext() {
		return block != null;
	}

	@Override
	public Block next() {
		if(!hasNext()) {
			throw new NoSuchElementException("No Such Element");
		}
		Block returnValue = block;
		getNextItem();
		return returnValue;
	}
	
	private void getNextItem() {
		while(iterator.hasNext()) {
			block = iterator.next();
			if(predicate.test(block))
				return;
		}
		block = null;
	}

}
