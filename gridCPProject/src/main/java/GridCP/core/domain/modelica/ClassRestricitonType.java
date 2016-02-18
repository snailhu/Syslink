package GridCP.core.domain.modelica;

public enum ClassRestricitonType {
	
	MODEL
	{
		@Override
		public String getName() {
			return "modle";
		}
	},
	
	PACKAGE
	{
		@Override
		public String getName() {
			return "package";
		}
	},

	RECORD
	{
		@Override
		public String getName() {
			return "record";
		}
	},

	FUNCTION
	{
		@Override
		public String getName() {
			return "function";
		}
	},

	CONNECTOR
	{
		@Override
		public String getName() {
			return "connector";
		}
	},

	BLOCK
	{
		@Override
		public String getName() {
			return "block";
		}
	},

	TYPE
	{
		@Override
		public String getName() {
			return "type";
		}
	},

	CLASS
	{
		@Override
		public String getName() {
			return "class";
		}
	},

	OPERATOR
	{
		@Override
		public String getName() {
			return "operator";
		}
	},

	OPERATOR_FUNCTION
	{
		@Override
		public String getName() {
			return "operator_function";
		}
	},

	ENUMERATION
	{
		@Override
		public String getName() {
			return "enumeration";
		}
	};
	
	public abstract String getName();
}
