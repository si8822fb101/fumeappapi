//package edu.ics499.fumeappapi.domain;
//
//import java.util.Iterator;
//import java.util.NoSuchElementException;
//
//public class ReadUserIterator implements Iterator<Result> {
//	private Iterator<User> iterator;
//	private Result result = new Result();
//
//
//	public ReadUserIterator(Iterator<User> iterator) {
//		this.iterator = iterator;
//	}
//
//	@Override
//	public boolean hasNext() {
//		return iterator.hasNext();
//	}
//
//	@Override
//	public Result next() {
//		if(iterator.hasNext()) {
//			result.setUserInformation(iterator.next());
//		} else {
//			throw new NoSuchElementException("No such element");
//		}
//		return result;
//	}
//
//}
