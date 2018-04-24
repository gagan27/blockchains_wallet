package agent;

import java.security.PublicKey;

public class Transaction {
	public String transactionId; //Contains a hash of transaction*

	public PublicKey sender; //Senders address/public key.

	public PublicKey reciepient; //Recipients address/public key.

	public float value; //Contains the amount we wish to send to the recipient.

}
