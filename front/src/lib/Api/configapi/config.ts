import {env} from '$env/dynamic/private';
export const API_CONFIG = {
	BACKEND_URL: env.BACKEND_URL,
	ENDPOINTS: {
		TOTO: {
			TOTO: '/TOTO/TOTO'
		}
	}
} as const;