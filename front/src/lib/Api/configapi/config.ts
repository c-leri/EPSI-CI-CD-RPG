import {env} from '$env/dynamic/public';

export const API_CONFIG = {
	PUBLIC_BACKEND_URL : env.PUBLIC_BACKEND_URL,
	ENDPOINTS: {
		Game: {
			gameGet: '/game/{id}'
		}
	}
} as const;