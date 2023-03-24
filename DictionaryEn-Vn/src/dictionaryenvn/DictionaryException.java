/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionaryenvn;

/**
 *
 * @author Do Huynh Anh Vu CE171446
 */
public class DictionaryException extends Exception {

    /**
     * Creates new ComicException
     *
     * @param message
     */
    public DictionaryException(String message) {
        super(message);
    }

    /**
     * Gets the exception message
     *
     * @return
     */
    @Override
    public String getMessage() {
        return "DictionaryException: " + super.getMessage();
    }
}
