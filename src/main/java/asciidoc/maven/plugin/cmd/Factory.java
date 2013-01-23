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
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;

import asciidoc.maven.plugin.tools.ZipHelper;

public class Factory {

    List<Abstract> cmds;

    Log log = new SystemStreamLog();

    public Factory() {
        File asciidocDir = findAsciidocHome();
        this.cmds = new ArrayList<Abstract>();
        this.cmds.add(new Asciidoc(asciidocDir));
        this.cmds.add(new A2x(asciidocDir));
    }

    private File findAsciidocHome() {
        try {
            ZipHelper helper = new ZipHelper();
            File jarFile = new File(Factory.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            File asciidocHome = helper.unzipEntry(jarFile, "asciidoc");
            if (this.log.isInfoEnabled())
                this.log.info("asciiDocHome: " + asciidocHome);
            return asciidocHome;
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Error getting jar file path",e);
        }
        
    }

    public CmdI getCommand(String _value) {
        for (Abstract cmd : this.cmds) {
            if (cmd.getId().equals(_value))
                return cmd;
        }
        throw new IllegalArgumentException("Unknown command: '" + _value + "'");
    }

}
