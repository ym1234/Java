public class Memory {

	Address[] memory;

	public Memory(int size){
		memory = new Address[size];
	}

	private class Address {
		byte instruction = 0;
		byte value = 0;

		public Address(byte instruction, byte value) {
			this.instruction = instruction;
			this.value = value;
		}
	}

	public int getInstructionAt(int index){
		return memory[index].instruction;
	}

	public int getValueAt(int index) {
		return memory[index].value;
	}

	public int getAsDat(int index) {
		return memory[index].value + memory[index].instruction * 100;
	}
}
