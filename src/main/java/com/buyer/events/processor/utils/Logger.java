package com.buyer.events.processor.utils;

import com.sol.qa.util.XlsReader;

public class Logger {

  /**
   * This method have arg1 , arg2 , agr3 , arg4 as the parameter.
   */
  public void logCommand(
      final String prefix,
      final String command,
      final String arg1,
      final String arg2,
      final String arg3,
      final String arg4
  ) {
    String[] arguments = {arg1, arg2, arg3, arg4 };
    System.out.println(
        prefix + " "
            + "Command: " + command + " "
            + "Args: " + String.join(", ", arguments)
    );
    }

  /**
   * This method gives the path file of the sheet name given.
   */
  public String getLogPrefix(final XlsReader reader, final String sheetName) {
    String[] paths = reader.getPath().split("/", 0);
    return paths[paths.length - 1] + " >> " + sheetName;
    }
  }
