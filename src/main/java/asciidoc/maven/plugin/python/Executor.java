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
package asciidoc.maven.plugin.python;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;
import org.python.core.PyString;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

import asciidoc.maven.plugin.tools.FileHelper;

/**
 * 
 * @author cbadenes
 *
 */
public class Executor {

    private Log log = new SystemStreamLog();
    
    
    public void exec(LinkedList<String> _linkedList, File _file){
        PySystemState sys = new PySystemState();
        sys.argv.clear();
        
        Iterator<String> iterator = _linkedList.iterator();
        while(iterator.hasNext()){
            sys.argv.append(new PyString(iterator.next()));
        }
        
        PythonInterpreter python = new PythonInterpreter(null, sys);
        python.set("__file__", _file.getAbsolutePath());
        long before = System.currentTimeMillis();
        log.info("executing:  '" + _file.getName() + " " + _linkedList);        
        python.exec(FileHelper.readFile(_file));
        log.info("Executed in " + ((System.currentTimeMillis() - before) / 1000d) + "s");
    }
    
    
    
    
    
}

