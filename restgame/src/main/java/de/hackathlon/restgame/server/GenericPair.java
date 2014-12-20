package de.hackathlon.restgame.server;

public class GenericPair<A,B>{
		A firstValue;
		B secondValue;
		
		public GenericPair(A firstValue, B secondValue){
			this.firstValue=firstValue;
			this.secondValue=secondValue;
		}

		public A getFirstValue() {
			return firstValue;
		}

		public void setfirstValue(A firstValue) {
			this.firstValue = firstValue;
		}

		public B getSecondValue() {
			return secondValue;
		}

		public void setSecondValue(B secondValue) {
			this.secondValue = secondValue;
		}
		
		
	}