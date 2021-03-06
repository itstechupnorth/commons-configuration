/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.configuration.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for NodeAddData.
 *
 * @author <a
 * href="http://commons.apache.org/configuration/team-list.html">Commons
 * Configuration team</a>
 * @version $Id$
 */
public class TestNodeAddData
{
    /** Constant for the default parent node used for testing. */
    private static final ConfigurationNode TEST_PARENT = new DefaultConfigurationNode(
            "parent");

    /** Constant for the name of the new node. */
    private static final String TEST_NODENAME = "testNewNode";

    /** Constant for the name of a path node. */
    private static final String PATH_NODE_NAME = "PATHNODE";

    /** Constant for the number of path nodes to be added. */
    private static final int PATH_NODE_COUNT = 10;

    /** The object to be tested. */
    NodeAddData addData;

    @Before
    public void setUp() throws Exception
    {
        addData = new NodeAddData(TEST_PARENT, TEST_NODENAME);
    }

    /**
     * Tests the default values of an uninitialized instance.
     */
    @Test
    public void testUninitialized()
    {
        addData = new NodeAddData();
        assertNull("A parent is set", addData.getParent());
        assertNull("Node has a name", addData.getNewNodeName());
        assertFalse("Attribute flag is set", addData.isAttribute());
        assertTrue("Path nodes are not empty", addData.getPathNodes().isEmpty());
    }

    /**
     * Tests the constructor that initializes the most important fields.
     */
    @Test
    public void testInitialized()
    {
        assertSame("Wrong parent", TEST_PARENT, addData.getParent());
        assertEquals("Wrong node name", TEST_NODENAME, addData.getNewNodeName());
        assertFalse("Attribute flag is set", addData.isAttribute());
        assertTrue("Path nodes are not empty", addData.getPathNodes().isEmpty());
    }

    /**
     * Tests adding path nodes.
     */
    @Test
    public void testAddPathNode()
    {
        for (int i = 0; i < PATH_NODE_COUNT; i++)
        {
            addData.addPathNode(PATH_NODE_NAME + i);
        }

        List<String> nodes = addData.getPathNodes();
        assertEquals("Incorrect number of path nodes", PATH_NODE_COUNT, nodes
                .size());
        for (int i = 0; i < PATH_NODE_COUNT; i++)
        {
            assertEquals("Wrong path node at position" + i, PATH_NODE_NAME + i,
                    nodes.get(i));
        }
    }
}
