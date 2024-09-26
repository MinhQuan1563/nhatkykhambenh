package com.nhom27.nhatkykhambenh.exception;

public class SaveDataException extends RuntimeException{
    public SaveDataException(String objName){
        super("Fail to save " + objName);
    }
}
