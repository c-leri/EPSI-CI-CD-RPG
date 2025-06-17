import type { ApiResponse,Game } from '$lib/types/api';
import { API_CONFIG } from '$lib/Api/configapi/config';

export const roomApi = {
	async getGame(id: string): Promise<ApiResponse<Game>> {
		console.log(id)
		console.log(`${API_CONFIG.BACKEND_URL}${API_CONFIG.ENDPOINTS.Game.gameGet.replace('{id}', id)}`)
		const response = await fetch(`${API_CONFIG.BACKEND_URL}/${API_CONFIG.ENDPOINTS.Game.gameGet.replace('{id}', id)}`);
		const data: ApiResponse<Game> = await response.json();

		if (!response.ok) {
			throw new Error(data.message || 'Erreur gameNotFound');
		}

		return data;
	}
};
