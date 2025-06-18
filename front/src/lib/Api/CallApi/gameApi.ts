import type { ApiResponse, Game } from '$lib/types/api';
import { API_CONFIG } from '$lib/Api/configapi/config';

export const gameApi = {
	async createGame(): Promise<number> {
		const response = await fetch(
			`${API_CONFIG.BACKEND_URL}/${API_CONFIG.ENDPOINTS.Game.gameCreate}`,
			{
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				}
			}
		);

		if (!response.ok) {
			throw new Error('Erreur lors de la création du jeu');
		}

		const gameId = await response.text();
		return parseInt(gameId);
	},

	async getAllGames(): Promise<Game[]> {
		const response = await fetch(
			`${API_CONFIG.BACKEND_URL}/${API_CONFIG.ENDPOINTS.Game.getAllGames}`
		);

		if (!response.ok) {
			throw new Error('Erreur lors de la récupération des jeux');
		}

		const data: Game[] = await response.json();
		console.log('All games data:', data);
		return data;
	},

	async loadGame(id: number): Promise<ApiResponse<Game>> {
		console.log('loadGame called with id:', id);
		const response = await fetch(
			`${API_CONFIG.BACKEND_URL}/${API_CONFIG.ENDPOINTS.Game.gameGet.replace('{id}', id.toString())}`
		);

		if (!response.ok) {
			throw new Error('Jeu non trouvé');
		}

		const data: ApiResponse<Game> = await response.json();
		console.log('Response data:', data);
		return data;
	},

	async playNextRoom(id: number): Promise<ApiResponse<Game>> {
		console.log('playNextRoom called with id:', id);

		const response = await fetch(
			`${API_CONFIG.BACKEND_URL}/${API_CONFIG.ENDPOINTS.Game.gameNextRoom.replace('{id}', id.toString())}`,
			{
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				}
			}
		);

		const data: ApiResponse<Game> = await response.json();
		console.log('Response data:', data);
		return data;
	}
};
