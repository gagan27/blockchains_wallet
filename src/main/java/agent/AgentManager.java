package agent;

import java.util.ArrayList;
import java.util.List;

public class AgentManager {

    private List<Agent> agents = new ArrayList<>();
    private static final Block root = new Block(0, "ROOT_HASH", "ROOT","None","None",0);

    public Agent addAgent(String name, int port,int balance) {
        Agent a = new Agent(name, "localhost", port, balance, root, agents);
        a.startHost();
        agents.add(a);
        return a;
    }

    public Agent getAgent(String name) {
        for (Agent a : agents) {
            if (a.getName().equals(name)) {
                return a;
            }
        }
        return null;
    }

    public List<Agent> getAllAgents() {
        return agents;
    }

    public void deleteAgent(String name) {
        final Agent a = getAgent(name);
        if (a != null) {
            a.stopHost();
            agents.remove(a);
        }
    }
    
    

    public List<Block> getAgentBlockchain(String name) {
        final Agent agent = getAgent(name);
        if (agent != null) {
            return agent.getBlockchain();
        }
        return null;
    }

    public void deleteAllAgents() {
        for (Agent a : agents) {
            a.stopHost();
        }
        agents.clear();
    }

    public void addAmount(String rec,int new_bal)
    {
    	final Agent a=getAgent(rec);
    	a.setBalance(a.getBalance()+new_bal);
    }
    
    public void subAmount(String sender,int new_bal)
    {
    	final Agent a=getAgent(sender);
//    	if (a.getBalance()-new_bal>0)
    	a.setBalance(a.getBalance()-new_bal);
    }
    
    public int getBalance(String sender)
    {
    	final Agent a=getAgent(sender);
    	return a.getBalance();
    	
    }
    public Block createBlock(final String name,final String rec,final int amt) {
        final Agent agent = getAgent(name);
        /*subAmount(name, amt);
        addAmount(rec, amt);*/
        if (agent != null) {
            return agent.createBlock(name,rec,amt);
        }
        return null;
    }
}
