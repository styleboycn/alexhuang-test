package com.alexhuang.utils.dto;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alexhuang.utils.json.JsonUtils;
import com.alexhuang.utils.json.TypeRef;

public class PvsProductConfig {

	public PvsProductConfig() {
	}

	public PvsProductConfig(String json) {
		if (json != null) {
			List<Dto> dtoList = JsonUtils.json2Object(json, new TypeRef<List<Dto>>() {
			});
			for (Dto dto : dtoList) {
				three2Dto.put(new Key(dto), dto);
				code2Dto.put(dto.getProductCode(), dto);
			}
		}
	}

	public String getProductCode(String expressType, String limitType, String cargoType) {
		Dto dto = three2Dto.get(new Key(expressType, limitType, cargoType));
		if (dto != null)
			return dto.getProductCode();
		return null;
	}
	
	public Dto getCodes(String productCode) {
		return code2Dto.get(productCode);
	}

	public Collection<Dto> getCodes() {
		return this.code2Dto.values();
	}

	private Map<Key, Dto> three2Dto = new HashMap<>();
	private Map<String, Dto> code2Dto = new HashMap<>();
	
	private static class Key {
		private String productExpressType;
		private String productLimitType;
		private String productCargoType;

		public Key(Dto dto) {
			super();
			this.productExpressType = dto.getProductExpressType();
			this.productLimitType = dto.getProductLimitType();
			this.productCargoType = dto.getProductCargoType();
		}

		public Key(String productExpressType, String productLimitType, String productCargoType) {
			super();
			this.productExpressType = productExpressType;
			this.productLimitType = productLimitType;
			this.productCargoType = productCargoType;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((productCargoType == null) ? 0 : productCargoType.hashCode());
			result = prime * result + ((productExpressType == null) ? 0 : productExpressType.hashCode());
			result = prime * result + ((productLimitType == null) ? 0 : productLimitType.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Key other = (Key) obj;
			if (productCargoType == null) {
				if (other.productCargoType != null)
					return false;
			} else if (!productCargoType.equals(other.productCargoType))
				return false;
			if (productExpressType == null) {
				if (other.productExpressType != null)
					return false;
			} else if (!productExpressType.equals(other.productExpressType))
				return false;
			if (productLimitType == null) {
				if (other.productLimitType != null)
					return false;
			} else if (!productLimitType.equals(other.productLimitType))
				return false;
			return true;
		}

	}

	public static class Dto {
		private String productName;
		private String productCode;
		private String productExpressType;
		private String productLimitType;
		private String productCargoType;

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public String getProductCode() {
			return productCode;
		}

		public void setProductCode(String productCode) {
			this.productCode = productCode;
		}

		public String getProductExpressType() {
			return productExpressType;
		}

		public void setProductExpressType(String productExpressType) {
			this.productExpressType = productExpressType;
		}

		public String getProductLimitType() {
			return productLimitType;
		}

		public void setProductLimitType(String productLimitType) {
			this.productLimitType = productLimitType;
		}

		public String getProductCargoType() {
			return productCargoType;
		}

		public void setProductCargoType(String productCargoType) {
			this.productCargoType = productCargoType;
		}
	}

}
