/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.netbeans.modules.maven.model.pom.impl;

import java.util.*;
import org.netbeans.modules.maven.model.pom.*;
import org.w3c.dom.Element;

/**
 *
 * @author mkleint
 */
public class DistributionManagementImpl extends POMComponentImpl implements DistributionManagement {

    public DistributionManagementImpl(POMModel model, Element element) {
        super(model, element);
    }
    
    public DistributionManagementImpl(POMModel model) {
        this(model, createElementNS(model, model.getPOMQNames().DISTRIBUTIONMANAGEMENT));
    }

    // attributes

    // child elements
    @Override
    public DeploymentRepository getRepository() {
        List<DeploymentRepository> childs = getChildren(DeploymentRepository.class);
        for (DeploymentRepository repo : childs) {
            if (getModel().getPOMQNames().DIST_REPOSITORY.getName().equals(repo.getPeer().getLocalName())) {
                return repo;
            }
        }
        return null;
    }

    @Override
    public void setRepository(DeploymentRepository distRepository) {
        List<Class<? extends POMComponent>> empty = Collections.emptyList();
        setChild(DeploymentRepository.class, getModel().getPOMQNames().DIST_REPOSITORY.getName(), distRepository, empty);
    }

    @Override
    public DeploymentRepository getSnapshotRepository() {
        List<DeploymentRepository> childs = getChildren(DeploymentRepository.class);
        for (DeploymentRepository repo : childs) {
            if (getModel().getPOMQNames().DIST_SNAPSHOTREPOSITORY.getName().equals(repo.getPeer().getLocalName())) {
                return repo;
            }
        }
        return null;
    }

    @Override
    public void setSnapshotRepository(DeploymentRepository distSnapshotRepository) {
        List<Class<? extends POMComponent>> empty = Collections.emptyList();
        setChild(DeploymentRepository.class, getModel().getPOMQNames().DIST_SNAPSHOTREPOSITORY.getName(), distSnapshotRepository, empty);
    }

    @Override
    public Site getSite() {
        return getChild(Site.class);
    }

    @Override
    public void setSite(Site site) {
        List<Class<? extends POMComponent>> empty = Collections.emptyList();
        setChild(Site.class, getModel().getPOMQNames().SITE.getName(), site, empty);
    }

    @Override
    public void accept(POMComponentVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getDownloadUrl() {
        return getChildElementText(getModel().getPOMQNames().DOWNLOADURL.getQName());
    }

    @Override
    public void setDownloadUrl(String url) {
        setChildElementText(getModel().getPOMQNames().DOWNLOADURL.getName(), url,
                getModel().getPOMQNames().DOWNLOADURL.getQName());
    }

}
