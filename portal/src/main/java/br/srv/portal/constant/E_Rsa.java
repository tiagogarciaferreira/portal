package br.srv.portal.constant;

public enum E_Rsa{

	ALGORITHM("RSA"),
	TAMANHO_CHAVE("1024"),
	CHAVE_PUBLICA("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCt0SxMKMcM7ELI5CPb4/jOrpgienH0dX5DcbFdyzRQ4rqj3tj2yMdVik8upV1lkSSJeS+bx9qIp+7LvNPofUr/UJadqvLrgbT+37nSa8zuLA1hiu1nbIvrz8+bGicDrIdrkql2cyQFNtmhE909GIQHYkcuDN2b8CUmZxMKHdVJTQIDAQAB"),
	CHAVE_PRIVADA("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAK3RLEwoxwzsQsjkI9vj+M6umCJ6cfR1fkNxsV3LNFDiuqPe2PbIx1WKTy6lXWWRJIl5L5vH2oin7su80+h9Sv9Qlp2q8uuBtP7fudJrzO4sDWGK7Wdsi+vPz5saJwOsh2uSqXZzJAU22aET3T0YhAdiRy4M3ZvwJSZnEwod1UlNAgMBAAECgYAApwZZitWkcMaXZnPUAXkPnYwopPkEh+RfcG/QAFFD9HXq441A7+P/qDM/45MELJVMhs3s8cUaOjclQKIy/zVm2lo9i9SGCxTS8s0JGJjpOnCbSj+qog6aAyiRvhr14wPsF4VU6WdsnryYIkzgy3mlbynVFf0vCeDHLsuy/muEKQJBAOHOFW0ybHatcwokKGW16Hu9tbrSu1PFj6EiSFCKzWU+w1Gy4BFX16eRWl+Z30QpJ/EQMa5YnJwatb54kwuXZFcCQQDFD2bd8H90RfRKJ3ZflBTu7Ay+dVT4RT9LNcBk+eD4ZKSrtZ3xDsEqHb59GJnR0I+CoN9qugq2DX+r2R+IWFj7AkAS9Pg7iKYueHVV/p8dmt3/tXeSTarIZ6edQYGMsXp464H+U+QX0KYcPUm3CPYDhOkDhfaFyo6rVFq5uOmvaXBVAkAQ+gi0qhlw3O30zX2HMmyzQKbAab9OPDj9s73FdTmKbLE7EcRvlgPSKwnUISBvUPZsHIvi1/BASt1q4qOyzVmtAkEAxKZAERPqb4VUpTnlNtZzt0bLlJVqSE/47hZIlV1VdmqjlrybdSk1H4agVyPAaKshpqzRpFzubHsqugb+vLRFow==");
	
	private String value;

	E_Rsa(String value) {
		this.value = value;
	}

	public String get() {
		return value;
	}
}

