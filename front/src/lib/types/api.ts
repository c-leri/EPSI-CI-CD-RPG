export type Game = {
	id: number;
	pv: number;
	nbSalle: number;
	lastDiceRoll: number;
};

export type ApiResponse<T> = {
	status: string;
	message: string;
	data: T;
};