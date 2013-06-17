/*******************************************************************************
 * Copyright (c) 2013 Carlos Badenes Olmedo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributor(s):
 *     cbadenes
 ******************************************************************************/
package asciidoc.maven.plugin.cmd;

import java.io.File;
import java.util.LinkedList;

public interface CmdI {
    
    CmdI withFormat(String _value);
    
    CmdI withTraductor(String _value);
    
    CmdI withSrc(File _file);
    
    CmdI withOutput(File _file);
    
    CmdI withEncoding(String _string);
    
    CmdI withBook(boolean book);
    
    CmdI withStylesheet(String _file);
    
    CmdI withLanguage(String _lang);

    CmdI withIcons(boolean _icons);
    
    CmdI withIconsDir(String _file);
    
    CmdI withVerbose(boolean _verbose);
    
    CmdI withNonHeaderFooter(boolean _noHeaderFooter);
    
    File getProgram();
    
    LinkedList<String> getOptions();

    File getOutputdir();
}

