package web;

import agent.Agent;
import agent.AgentManager;
import agent.Block;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(path = "/agent")
public class AgentController {

    private static AgentManager agentManager = new AgentManager();
    //private static TransactionCrypto transaction

    @RequestMapping(method = GET)
    public Agent getAgent(@RequestParam("name") String name) {
        return agentManager.getAgent(name);
    }

    @RequestMapping(method = DELETE)
    public void deleteAgent(@RequestParam("name") String name) {
        agentManager.deleteAgent(name);
    }

    @RequestMapping(path = "new", method = POST, params = {"name", "port","balance"})
    public Agent addAgent(@RequestParam("name") String name, @RequestParam("port") int port,
    		@RequestParam("balance") int balance) {
        return agentManager.addAgent(name, port,balance);
    }

    @RequestMapping(path = "all", method = GET)
    public List<Agent> getAllAgents() {
        return agentManager.getAllAgents();
    }

    @RequestMapping(path = "all", method = DELETE)
    public void deleteAllAgents() {
        agentManager.deleteAllAgents();
    }

    @RequestMapping(method = POST, path = "mine")
    public Block createBlock(@RequestParam(value = "agent") final String name,
    		@RequestParam(value = "rec") final String rec,
    		@RequestParam(value = "amt") final int amt) {
        return agentManager.createBlock(name,rec,amt);
    }
    
    @RequestMapping(method = POST, params = {"receiver","amount"})
    public void addAmount(@RequestParam("receiver") String rec, 
    		@RequestParam("amount") int new_bal) {
    	agentManager.addAmount(rec, new_bal);
    }
    
    @RequestMapping(method = POST, params = {"sender","amount"})
    public void subAmount(@RequestParam("sender") String sender, 
    		@RequestParam("amount") int new_bal) {
    	agentManager.subAmount(sender, new_bal);
    }
    
    @RequestMapping(path="balance", method = POST)
    public void getBalance(@RequestParam("sender") String sender) {
    	agentManager.getBalance(sender);
    }
}