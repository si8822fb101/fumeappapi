package edu.ics499.fumeappapi.storage;

import edu.ics499.fumeappapi.storage.StorageException;

public class StorageFileNotFoundException extends StorageException {
    public StorageFileNotFoundException(String message){
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

}
