package de.hackathlon.restgame.server;

public class Pair{
		long lastTimeUpdated;
		boolean inGame;
		
		public Pair(long time, boolean inGame){
			this.lastTimeUpdated=time;
			this.inGame=inGame;
		}

		public long getLastTimeUpdated() {
			return lastTimeUpdated;
		}

		public void setLastTimeUpdated(long lastTimeUpdated) {
			this.lastTimeUpdated = lastTimeUpdated;
		}

		public boolean isInGame() {
			return inGame;
		}

		public void setInGame(boolean inGame) {
			this.inGame = inGame;
		}
		
		
	}