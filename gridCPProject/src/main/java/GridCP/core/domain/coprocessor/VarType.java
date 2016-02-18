package GridCP.core.domain.coprocessor;

public enum VarType {
	
	/** 布尔型数组 */
	BOOLEANARRAY
	{
		@Override
		public String getName() {
			return "boolean[]";
		}
	},
	
	/** 字符创型数组 */
	STRINGGARRAY
	{
		@Override
		public String getName() {
			return "String[]";
		}
	},
	/** 整型数组 */
	INTEGERARRAY
	{
		@Override
		public String getName() {
			return "integer[]";
		}
	},
	/** 双精度数组 */
	DOUBLEARRAY
	{
		@Override
		public String getName() {
			return "double[]";
		}
	},
	/** 文件类型 */
	FILE
	{
		@Override
		public String getName() {
			return "file";
		}
	},
	/** geometry型 */
	GEOMETRY
	{
		@Override
		public String getName() {
			return "geometry";
		}
	},
	/** 布尔型 */
	BOOLEAN
	{
		@Override
		public String getName() {
			return "boolean";
		}
	},
	/** 字符串 */
	STRING
	{
		@Override
		public String getName() {
			return "string";
		}
	},
	/** 整数 */
	INTEGER
	{
		@Override
		public String getName() {
			return "integer";
		}
	},
	/** 双精度 */
	DOUBLE
	{
		@Override
		public String getName() {
			return "double";
		}
	},
	/** 未知 */
	UNKNOWN
	{
		@Override
		public String getName() {
			return "unknown";
		}
	};
	
	public abstract String getName();
}
