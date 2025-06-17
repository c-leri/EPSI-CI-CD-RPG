import {env} from '$env/dynamic/public';

export const API_CONFIG = {
	BACKEND_URL : env.PUBLIC_BACKEND_URL,
	ENDPOINTS: {
		Game: {
			gameGet: 'game/{id}',
			gameNextRoom: 'game/{id}/play'
		}
	}
} as const;