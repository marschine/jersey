/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * http://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package org.glassfish.jersey.tests.integration.jersey2031;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.glassfish.jersey.server.mvc.Template;
import org.glassfish.jersey.server.mvc.Viewable;

/**
 * Test resource.
 *
 * @author Michal Gajdos (michal.gajdos at oracle.com)
 */
@Path("/")
public class Issue2031Resource {

    private final static Model model;
    private final static String absolutePath;

    static {
        absolutePath = "/" + Issue2031Resource.class.getName().replaceAll("\\.", "/").replace('$', '/') + "/index.jsp";
        model = new Model();
    }

    @GET
    @Path("viewable-relative")
    @Produces("text/html")
    public Viewable viewableRelative() {
        return new Viewable("index", model);
    }

    @GET
    @Path("viewable-absolute")
    @Produces("text/html")
    public Viewable viewableAbsolute() {
        return new Viewable(absolutePath, model);
    }

    @GET
    @Path("template-relative")
    @Produces("text/html")
    @Template(name = "index")
    public Model templateRelative() {
        return model;
    }

    @GET
    @Path("template-absolute")
    @Produces("text/html")
    @Template(
            name = "/org/glassfish/jersey/tests/integration/jersey2031/Issue2031Resource/index.jsp")
    public Model templateAbsolute() {
        return model;
    }

    public static class Model {

        private String index = "index";
        private String include = "include";

        public String getIndex() {
            return index;
        }

        public String getInclude() {
            return include;
        }
    }
}
