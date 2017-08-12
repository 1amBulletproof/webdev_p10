/** BadQueryStringException Class
 *  - Extends Exception class
 * @author Brandon Tarney
 * @since 6/16/2017
 */
package com.brandontarney.service;

public class BadQueryStringException extends Exception{
    
    public BadQueryStringException(String msg) {
        super(msg);
    }
    
}
