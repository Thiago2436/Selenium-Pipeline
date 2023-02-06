package com.buyer.events.processor;

import java.awt.AWTException;
import java.text.ParseException;

import com.sol.qa.util.XlsReader;

public interface ITabCommandProcessor {

  void processCommand(
      XlsReader reader,
      String command,
      String argument1,
      String argument2,
      String argument3,
      String argument4
      ) throws AWTException, InterruptedException, ParseException;

  }
