import { env } from '$env/dynamic/public';

export const API_CONFIG = {
	BACKEND_URL: env.PUBLIC_BACKEND_URL,
	ENDPOINTS: {
		Game: {
			getAllGames: 'game/GetAll',
			gameCreate: 'game/',
			gameGet: 'game/{id}',
			gameNextRoom: 'game/{id}/play'
		}
	}
} as const;
