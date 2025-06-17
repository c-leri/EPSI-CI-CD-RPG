import type { ApiResponse,Game } from '$lib/types/api';
import { API_CONFIG } from '$lib/Api/configapi/config';

export const roomApi = {
	async getGame(): Promise<ApiResponse<Game>> {
		const response = await fetch(`${API_CONFIG.PUBLIC_BACKEND_URL}${API_CONFIG.ENDPOINTS.Game.gameGet}`.replace('{id}', '1'));
		const data: ApiResponse<Game> = await response.json();

		if (!response.ok) {
			throw new Error(data.message || 'Erreur gameNotFound');
		}

		return data;
	}
};