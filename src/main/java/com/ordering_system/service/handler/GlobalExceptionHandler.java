package com.ordering_system.service.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.*;
import java.util.Date;


@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR_LOG_FILE = "error.log";
    private static final File  FILE = new File("stacktrace.log");



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorLogger.logError(logException(e));

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(e.getMessage());

        // Return a 500 status code
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }


    public static String logException(Exception e) {
        try {
            FileWriter fileWriter = new FileWriter(ERROR_LOG_FILE, true);
            fileWriter.write(new Date() + ": Error occurred: " + e.getMessage());
            fileWriter.write("\n");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }




        try {
            FileWriter fileWriter = new FileWriter(FILE);
            fileWriter.write("" + new Date() + " ");
            PrintStream out = new PrintStream(new FileOutputStream(FILE, true));
            e.printStackTrace(out);
            out.close();
            fileWriter.close();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


        return new Date() + ": Error occurred: " + e.getMessage();
    }


}