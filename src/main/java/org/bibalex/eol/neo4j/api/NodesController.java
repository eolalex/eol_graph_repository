package org.bibalex.eol.neo4j.api;

import org.bibalex.eol.neo4j.backend_api.Neo4jTree;
import org.bibalex.eol.neo4j.models.NodeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.bibalex.eol.neo4j.models.Node;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/neo4j")
public class NodesController {

    @Autowired
    private NodesService service;



    @RequestMapping(value="/createNode", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
     public int createNode(@RequestBody Node n)
    {
        int  generatedNodeId = service.createNode(n);
        return generatedNodeId;
    }

    @RequestMapping(value="/createSynonymNode", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
     public int createSynonym(@RequestBody Node n)
    {
        int  generatedNodeId = service.createSynonym(n);
        return generatedNodeId;
    }

     @RequestMapping(value="/createSynonymRelation", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
     public boolean createSynonymRelation(@RequestBody Node n)
    {
        boolean  created = service.createRelationBetweenNodeAndSynonyms(n);
        return created;
    }

     @RequestMapping(value="/createAncestorNode", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
     public int createAncestorNode(@RequestBody Node n)
    {
        int  generatedNodeId = service.createAncestorNode(n);
        return generatedNodeId;
    }


    @RequestMapping(value="/createParentWithPlaceholder", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
     public int createParentNode(@RequestBody Node n)
    {
        int  generatedNodeId = service.createParentNode(n);
        return generatedNodeId;
    }


    @RequestMapping(value="/deleteNodeAncestoryFormat", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public boolean deleteNodeAncestorForm(@RequestBody Node n)
    {
        boolean result = service.deleteNodeAncestoryFormat(n);
        return result;
    }


    @RequestMapping(value="/deleteNodeParentFormat", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public boolean deleteNodeParentForm(@RequestBody Node n)
    {
        boolean result = service.deleteNodeParentFormat(n);
        return result;
    }


    @RequestMapping(value="/getNode", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public int getNode(@RequestBody Node n)
    {
       int generatedNodeId =  service.getNode(n);
       return generatedNodeId;
    }

    @RequestMapping(value="/getAcceptedNode", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public int getAcceptedNode(@RequestBody Node n)
    {
       int generatedNodeId =  service.getAcceptedNode(n);
       return generatedNodeId;
    }


    @RequestMapping(value="/getSynonymNode", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public int getSynonymNode(@RequestBody Node n)
    {
       int generatedNodeId =  service.getSynonymNode(n);
       return generatedNodeId;
    }

    @RequestMapping(value="/getNodeData/{generatedNodeId}", method = RequestMethod.GET)
    public NodeData getNodeData(@PathVariable("generatedNodeId") String generatedNodeId)
    {
        NodeData data = service.getData(generatedNodeId);
        return data;
    }

    @RequestMapping(value="/getNeo4jUpdates", method = RequestMethod.POST, consumes = "text/plain")
    public ArrayList<Neo4jTree> getChangesfromTimestamp(@RequestBody String timestamp )
    {
        ArrayList<Neo4jTree> trees = service.getUpdates(timestamp);
        return trees;
    }

    @RequestMapping(value="/getResourceTrees/{resourceId}", method = RequestMethod.GET)
    public ArrayList<Neo4jTree> getResourceTrees(@PathVariable("resourceId") int resourceId)
    {
        ArrayList<Neo4jTree> trees = service.getResourceTrees(resourceId);
        return trees;

    }

    @RequestMapping(value="/getParentsOfNodes", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public HashMap<Integer,Integer> getParentNodes(@RequestBody ArrayList<Integer> nodeIds)
    {
        HashMap<Integer,Integer> parents = service.getParentNodes(nodeIds);
        return parents;
    }

    @RequestMapping(value="/getRootNodes", method = RequestMethod.GET, produces = "application/json")
    public ArrayList<Node> getRootNodes()
    {
        ArrayList <Node> roots = service.getRoots();
        return roots;
    }



}
