import {env} from '$env/dynamic/private';
export const API_CONFIG = {
	BACKEND_URL: env.BACKEND_URL,
	ENDPOINTS: {
		Game: {
			gameGet: '/game/{id}'
		}
	}
} as const;