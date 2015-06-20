package test;




import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

@Script.Manifest(name = "TEst", description = "used for testing")


public class Test extends PollingScript<ClientContext> {
	
	@Override
	public void poll() {
		System.out.println(ctx.players.local().tile().x() + " " + ctx.players.local().tile().y());
		System.exit(1);
	}
	
}