/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmw_managament;

/**
 *
 * @author PC
 */
public class PMWException extends Exception {
    /**
     * Creates new ComicException
     *
     * @param message
     */
    public PMWException(String message) {
        super(message);
    }

    /**
     * Gets the exception message
     *
     * @return
     */
    @Override
    public String getMessage() {
        return "Exception: " + super.getMessage();
    }
}
