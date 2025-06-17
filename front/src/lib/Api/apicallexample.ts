import type { ApiResponse,Game } from '$lib/types/api';
import { API_CONFIG } from '$lib/Api/configapi/config';

export const roomApi = {
	async getRooms(): Promise<ApiResponse<Game>> {
		const response = await fetch(`${API_CONFIG.BACKEND_URL}${API_CONFIG.ENDPOINTS.Game.gameGet}`.replace('{id}', '1'));
		const data: ApiResponse<Game> = await response.json();

		if (!response.ok) {
			throw new Error(data.message || 'Erreur lors de la récupération des salles');
		}

		return data;
	}
};