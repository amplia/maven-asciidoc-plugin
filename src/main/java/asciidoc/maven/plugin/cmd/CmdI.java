/*******************************************************************************
 * Copyright (c) 2013 Carlos Badenes Olmedo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Carlos Badenes Olmedo - initial API and implementation
 ******************************************************************************/
package asciidoc.maven.plugin.cmd;

import java.io.File;
import java.util.LinkedList;

public interface CmdI {
    
    CmdI withFormat(String _value);
    
    CmdI withSrc(File _file);
    
    CmdI withOutput(File _file);
    
    CmdI withStylesheet(String _file);

    CmdI withIcons(boolean _icons);
    
    CmdI withIconsDir(String _file);
    
    File getProgram();
    
    LinkedList<String> getOptions();

    File getOutputdir();

}

