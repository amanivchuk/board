export interface UserAuthorizationJWT {
  email: string
  password: string
}

export interface FbAuthorizationResponseJWT {
  token: string
  expiration: string
}
