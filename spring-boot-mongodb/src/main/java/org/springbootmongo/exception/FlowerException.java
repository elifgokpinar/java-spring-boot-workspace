package org.springbootmongo.exception;

public class FlowerException extends  Exception{

    public FlowerException(String msg) {
        super(msg);
    }

    public static String FlowerAlreadyExistException(String id){
        return "Flower is already exist. Id: " + id;
    }

    public static String FlowerAlreadyExistByNameException(String name){
        return "Flower is already exist. Name: " + name;
    }

    public static String FlowerNotFoundException(String id){
        return "Flower can not found. Id: " + id;
    }
}
